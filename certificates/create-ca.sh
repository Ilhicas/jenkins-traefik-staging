openssl genrsa -out ca-key.pem 4096
openssl req -new -x509  -nodes -days 365 -key ca-key.pem -sha256 -out ca.pem -subj "/C=PT/ST=Coimbra/L=Coimbra/O=ilhicas/OU=ilhicas/CN=ilhicas.com"