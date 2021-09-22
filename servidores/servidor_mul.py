import re
from connection import ConnectionHelper

def mul(a, b):
    r = a * b
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
        r = mul(a, b)
    else:
        r = "Operandos invalidos"
    return bytes(r, "utf-8")

def get_status(data):
    message = str(data)
    status = False
    if message == "b'ok'":
        status = True
    return status

helper = ConnectionHelper('localhost', 20005)
status = helper.registerAt('mul')

if status:
    while True:
        print('\nListening...')
        data, address = helper.sock.recvfrom(1024)

        print('\nReceived {} bytes from {}'.format(len(data), address))
        print(data)

        if data:
            # Performs sum operation
            result = process(data)

            # Send the result back to the client
            sent = helper.sock.sendto(result, address)
            print('\nSent {} bytes back to {}'.format(sent, address))