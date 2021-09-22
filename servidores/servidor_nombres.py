import socket
import re
from connection import ConnectionHelper

def decode(data):
    strData = str(data)
    decoded = {}
    match = re.search(
        r"[a-z]{3}:\d+\.\d+,\d+\.\d+|reg:[a-z]{3}",
        strData
    )
    if match:
        decoded['operator'] = strData[match.start() : strData.index(":")]
        decoded['data'] = strData[strData.index(":") + 1 : match.end()]
    return decoded

def ask_result_operation(server_address, data, sock):
    # Prepare the data to be sent
    message = bytes(data, 'utf-8')
    try:
        # Sent data
        sent = sock.sendto(message, server_address)

        # Receive response
        data, _ = sock.recvfrom(1024)
    except:
        data = b"Fallo en la conexion"
    return data

def process(operation, directory, sock, address):
    if operation['operator'] == 'reg':
        directory[operation['data']] = address
        result = b"ok"
    elif operation['operator'] in directory:
        result = ask_result_operation(directory[operation['operator']], operation['data'], sock)
    else:
        result = b"Operacion no disponible"
    return result

directory = {}

helper = ConnectionHelper('localhost', 20000)

# Listening to new messages
while True:
    print('\nListening...')
    data, address = helper.sock.recvfrom(4096)

    print('\nReceived {} bytes from {}'.format(len(data), address))
    print(data)

    if data:
        # Decode data
        decoded = decode(data)

        # Performs the action requested by the client
        result = process(decoded, directory, helper.sock, address)

        # Send the result back to the client
        sent = helper.sock.sendto(result, address)
        print('\nSent {} bytes back to {}'.format(sent, address))
