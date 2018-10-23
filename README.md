### [WORK IN PROGRESS]
Example for Single Node Load Balancer and Dynamic Staging Environments Using Jenkins and Traefik

### Using traefik with https

Default usage already comes with https tls enabled.

In order to Use Traefik with ssl you may use the certificates already created under certificates folder, and this is the default behaviour after cloning.

You may create a Certificate Authority by changing certificates/create-ca.sh

And in order to create a new self signed certificate , just run

```sh
./create-certificate.sh example.com
## Or if you need to have ip matching as well

./create-certificate.sh example.com 192.168.10.1
```

Since volume is host mapped then the traefik container will have access to the new files, following the names in the script.

If you change names on the file output, then change traefik/traefik.toml to match the new names.