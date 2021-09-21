import sys
import socket


# Create a UDP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Bind the socket to the port
server_address = ('localhost', 20000)
print('Starting up on {} port {}'.format(*server_address))
sock.bind(server_address)

while True:
    print('\nWaiting...')
    data, address = sock.recvfrom(4096)

    print('Received {} bytes from {}'.format(len(data), address))
    print(data)

    if data:
        sent = sock.sendto(b"Hola", address)
        print('Sent {} bytes back to {}'.format(sent, address))