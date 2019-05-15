
<script src="https://cdn.jsdelivr.net/gh/ethereum/web3.js/dist/web3.min.js"></script>


<script >



            'use strict';
            var Web3 = require('web3');
            var Tx = require('ethereumjs-tx');

            //initialize connection
            var web3 = new Web3();
            web3.setProvider(new web3.providers.HttpProvider('https://rinkeby.infura.io/v3/54faa11a6b5746aa8e09d9e08d31212c'));

            var toAccount = '0x34fCB356043C96C785ccEe41e38F3eE146b7A767';
            var fromAccount = '0xc78d53a4cc317dc3d3f7ee3d2ea639f77b2615d8';
            var privateKey = new Buffer.from('498EE239A44F1E3A48997CE39DB5DBAEEDBED972C43338D260887B06185FCFAF', 'hex');

            var gasPriceHex = web3.toHex(4100000000);
            var gasLimitHex = web3.toHex(21000);
            var value = web3.toHex(0.5); //amount to send in wei
            var nonce = web3.eth.getTransactionCount(fromAccount);
            var nonceHex = web3.toHex(nonce);

            var rawTx = {
                nonce: nonceHex,
                gasPrice: gasPriceHex,
                gasLimit: gasLimitHex,
                to: toAccount,
                from: fromAccount,
                value: value,
                data: ''
            };

            var tx = new Tx(rawTx);
            tx.sign(privateKey);

            var serializedTx = tx.serialize();
            console.log(serializedTx);

            web3.eth.sendRawTransaction('0x'+serializedTx.toString('hex'), function (err, hash) {
                if (err) {
                    console.log("" + err)
                }
                else {
                    console.log(hash);
                    alert(hash);
                }
            });


        </script>
