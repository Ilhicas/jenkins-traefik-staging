#!/usr/bin/env bash
HOST=$1
IP=$2
openssl genrsa -out server-key.pem 4096
openssl req -subj "/CN=${HOST:-'ilhicas.com'}" -sha256 -new -key server-key.pem -out server.csr
echo subjectAltName = DNS:${HOST:-"ilhicas.com"},IP:${IP:-"10.10.10.20"} >> extfile.cnf
openssl x509 -req -days 365 -sha256 -in server.csr -CA ca.pem -CAkey ca-key.pem \
  -CAcreateserial -out server-cert.pem -extfile extfile.cnf