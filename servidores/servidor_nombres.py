import socket
import re

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
    body = bytes(data, 'utf-8')

    try:
        # Sent data
        sent = sock.sendto(body, server_address)

        # Receive response
        data, _ = sock.recvfrom(4096)
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

# Create a UDP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Bind the socket to the port
server_address = ('localhost', 20000)
print('Starting up on {} port {}'.format(*server_address))
sock.bind(server_address)

# Listening to new messages
while True:
    print('\nListening...')
    data, address = sock.recvfrom(4096)

    print('\nReceived {} bytes from {}'.format(len(data), address))
    print(data)

    if data:
        # Decode data
        decoded = decode(data)

        # Performs the action requested by the client
        result = process(decoded, directory, sock, address)

        # Send the result back to the client
        sent = sock.sendto(result, address)
        print('\nSent {} bytes back to {}'.format(sent, address))
