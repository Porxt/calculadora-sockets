import socket

class ConnectionHelper:
    
    sock = None
    registration_status = False
    
    def __init__(self, host, port) -> None:
        # Create a UDP socket
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        # Bind the socket to the port
        server_address = (host, port)
        print('Starting up on {} port {}'.format(*server_address))
        self.sock.bind(server_address)

    def registerAt(self, host, port, service) -> bool:
        message = bytes("reg:" + service, "utf-8")
        # Sends a registration request to the name server
        sent = self.sock.sendto(message, (host, port))

        # Receive status
        data, _ = self.sock.recvfrom(1024)
        self.registration_status = ConnectionHelper._get_status(data)
        return self.registration_status

    @staticmethod
    def _get_status(self, data) -> bool:
        message = str(data)
        status = False
        if message == "b'ok'":
            status = True
        return status
