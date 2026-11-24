#!/usr/bin/env bash
set -e

rm -rf out
javac -d out src/main/java/lab/wallet/*.java

java -ea -cp out lab.wallet.WalletTest
java -cp out lab.wallet.Main