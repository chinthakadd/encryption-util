

1. Generating RSA 2048 Private Key Encrypted

1.1 Simplest form

```
openssl genrsa -out private_key.pem 2048
```

1.2 Encrypt with a Password

```
openssl genrsa -des3 -out private.pem 2048
```

2. Extract the Public Key from the key file

```
openssl rsa -in private.pem -outform PEM -pubout -out public.pem
```

3. Generate a PKCS#8 key for reading private key using java

The file was generated using genrsa which creates a PKCS#1 format (this is the format the OP loads, 
as can be seen by the BEGIN RSA PRIVATE KEY header), while PKCS#8 is a different format
 (and in PEM encoding can be detect due to the different header: BEGIN PRIVATE KEY). 
 
 My key generator now looks like this: 
 
 ```
 openssl genrsa 2048 | openssl pkcs8 -topk8 -nocrypt -out private_key_pkcs8.pem
 ```


### How do I Encrypt and Decrypt using OpenSSL

Here you have the commands you need to encrypt or decrypt using openssl:

Decrypt:

$ openssl rsautl -decrypt -in $ENCRYPTED -out $PLAINTEXT -inkey src/main/resources/private_key.pem

Encrypt:

$ openssl rsautl -encrypt -in $PLAINTEXT -out $PLAINTEXT.encrypt -pubin -inkey src/main/resources/public_key.pem

Refer to the sample shell script include in the root directory



