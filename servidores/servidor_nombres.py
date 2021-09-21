import socket
import re

directory = {}

def decode(data):
    strData = str(data)
    decoded = {}
    match = re.search(
        r"[a-z]{3}:([a-z]{3},)?\d+\.\d+(\.\d+\.\d+)?,\d+(\.\d+)?",
        strData
    )
    if match:
        decoded['operation'] = strData[match.start(): strData.index(":")]
        decoded['data'] = strData[strData.index(":") + 1: match.end()]
    return decoded

def call_server(server, data):
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    body = bytes(data[0] + "," + data[1], 'utf-8')
    try:
        # Sent data
        sent = sock.sendto(body, server)

        # Receive response
        data, _ = sock.recvfrom(4096)
    finally:
        sock.close()
    return data

def process(instr, directory):
    if instr['operation'] == 'reg':
        unfolded = instr['data'].split(",")
        directory[unfolded[0]] = tuple(unfolded[1:])
        result = "ok"
    elif instr['operation'] in directory:
        result = call_server(directory[instr['operation']], instr['data'])
    else:
        result = "Imposible conectar"
    return str(result)


# Create a UDP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Bind the socket to the port
server_address = ('localhost', 20000)
print('Starting up on {} port {}'.format(*server_address))
sock.bind(server_address)

while True:
    print('\nListening...')
    data, address = sock.recvfrom(4096)

    print('\nReceived {} bytes from {}'.format(len(data), address))
    print(data)

    if data:
        decoded = decode(data)
        result = process(decoded, directory)
        sent = sock.sendto(bytes(result, 'utf-8'), address)
        print('\nSent {} bytes back to {}'.format(sent, address))
