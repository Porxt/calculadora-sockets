import re
import socket

def div(a, b):
    if b == 0:
        return "Division por cero"
    
    r = a / b
    ri = int(r)
    if r == ri:
        return str(ri)
    return str(r)

def process(data):
    data = str(data)
    match = re.search(r"\d+\.\d+,\d+\.\d+", data)
    if match:
        a = float(data[match.start() : data.index(",")])
        b = float(data[data.index(",") + 1 : match.end()])
        r = div(a, b)
    else:
        r = "Operandos invalidos"
    return bytes(r, "utf-8")

def get_status(data):
    message = str(data)
    status = False
    if message == "b'ok'":
        status = True
    return status

# Create a UDP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Bind the socket to the port
server_address = ("localhost", 20006)
print('Starting up on {} port {}'.format(*server_address))
sock.bind(server_address)

# Sends a registration request to the name server
sent = sock.sendto(b"reg:div", ('127.0.0.1', 20000))

# Receive status
data, _ = sock.recvfrom(1024)

status = get_status(data)

if status:
    while True:
        print('\nListening...')
        data, address = sock.recvfrom(1024)

        print('\nReceived {} bytes from {}'.format(len(data), address))
        print(data)

        if data:
            # Performs sum operation
            result = process(data)

            # Send the result back to the client
            sent = sock.sendto(result, address)
            print('\nSent {} bytes back to {}'.format(sent, address))