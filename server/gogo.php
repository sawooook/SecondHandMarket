
<?php

$_GET['id'];


error_reporting(E_ALL);
ini_set('display_errors',1);

$conn=mysqli_connect('localhost','root','136253','store');


$sql = "SELECT * FROM SYtoken WHERE sy_index ='{$_GET['id']}'";
$result=mysqli_query($conn,$sql);
$board=mysqli_fetch_array($result);
$sy_id=$board['sy_id'];
$sy_address=$board['sy_address'];
$sy_type=$board['sy_type'];
$sy_amount=$board['sy_amount'];
$sy_confirm="지급완료";


$sqll="update SYtoken set sy_id='$sy_id',sy_address='$sy_address',
sy_type='$sy_type',
sy_amount='$sy_amount',
sy_confirm='$sy_confirm'
where sy_index={$_GET['id']}";
// echo $sy_address;
$result=mysqli_query($conn,$sqll);


$current_member = $sy_address;

// echo $current_member;

$resu3lt = substr($current_member, 0, 15);
// echo $resu3lt;
$trss = substr($current_member, 15);
// echo $trss;
// echo $resu3lt.$trss;
// $sqll="update SYtoken set sy_id='$sy_id',sy_address='$sy_address',
// sy_type='$sy_type',
// sy_amount='$sy_amount',
// sy_confirm='$sy_confirm'
// where sy_index='$_GET['id']'";
//
// $result=mysqli_query($conn,$sqll);



 ?>


<script src="https://cdn.jsdelivr.net/gh/ethereum/web3.js/dist/web3.min.js"></script>
<!--
<script src="https://github.com/ethereumjs/browser-builds/raw/2fb69a714afe092b06645286f14b94f41e5c062c/dist/ethereumjs-tx.js"></script>
-->
<script src="https://cdn.rawgit.com/ethereumjs/browser-builds/2fb69a714afe092b06645286f14b94f41e5c062c/dist/ethereumjs-tx.js"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

<script>



            'use strict';
            var Web3 = require('web3');

            //initialize connection
            var web3 = new Web3();
            web3.setProvider(new web3.providers.HttpProvider('https://ropsten.infura.io/v3/54faa11a6b5746aa8e09d9e08d31212c'));

            var toAccount ='<?php echo $current_member ?>' ;
            console.log(toAccount);
            var fromAccount = '0xc78d53a4cc317dc3d3f7ee3d2ea639f77b2615d8';
            // var privateKey = new Buffer.from('498EE239A44F1E3A48997CE39DB5DBAEEDBED972C43338D260887B06185FCFAF', 'hex');
            let privateKey = new EthJS.Buffer.Buffer('498EE239A44F1E3A48997CE39DB5DBAEEDBED972C43338D260887B06185FCFAF', 'hex')


            var gasPriceHex = web3.toHex(4100000000);
            var gasLimitHex = web3.toHex(21000);
            var value = web3.toHex(500000000000000000); //amount to send in wei
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

            let tx = new EthJS.Tx(rawTx)
            // var tx = new Tx(rawTx);
            tx.sign(privateKey);

            let serializedTx = tx.serialize();
            console.log(serializedTx);

            web3.eth.sendRawTransaction('0x'+serializedTx.toString('hex'), function (err, hash) {
                if (err) {
                    console.log("" + err);
                }

                else {
                    console.log(hash);
                    alert(hash);

                    location.href="SYtoken.php";



                }

            });


        </script>
