#!/usr/bin/env bash

ENCRYPTED=enc:$(openssl rsautl -encrypt -in ./content -pubin -inkey src/main/resources/public_key.pem| base64)
echo "ENCRYPTED: ${ENCRYPTED}"

DECRYPTED=$(openssl rsautl -decrypt -in ./content.encrypt  -inkey src/main/resources/private_key.pem)
echo "DECRYPTED: ${DECRYPTED}"
