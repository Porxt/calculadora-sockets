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

    def registerAt(self, service) -> bool:
        message = bytes("reg:" + service, "utf-8")
        # Sends a registration request to the name server
        sent = self.sock.sendto(message, ('localhost', 20000))

        # Receive status
        data, _ = self.sock.recvfrom(1024)
        self.registration_status = ConnectionHelper._get_status(data)
        return self.registration_status

    @staticmethod
    def _get_status(data) -> bool:
        message = str(data)
        status = False
        if message == "b'ok'":
            status = True
        return status

    @staticmethod
    def get_ip():
        host_name = socket.gethostname()
        ip = socket.gethostbyname(host_name)
        return ip
