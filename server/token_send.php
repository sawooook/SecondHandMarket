
<script src="https://cdn.jsdelivr.net/gh/ethereum/web3.js/dist/web3.min.js"></script>
<!--
<script src="https://github.com/ethereumjs/browser-builds/raw/2fb69a714afe092b06645286f14b94f41e5c062c/dist/ethereumjs-tx.js"></script>
-->
<script src="https://cdn.rawgit.com/ethereumjs/browser-builds/2fb69a714afe092b06645286f14b94f41e5c062c/dist/ethereumjs-tx.js"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>


<script type="text/javascript">
const Tx = require('ethereumjs-tx');
const Web3 = require('web3');
const web3 = new Web3(new Web3.providers.HttpProvider('https://ropsten.infura.io/v3/54faa11a6b5746aa8e09d9e08d31212c'));


const contractAddr = '0x823B4AE4191259c3f9eA5E112e4B646Db10a757D';
const contractAbi = [{"constant":false,"inputs":[{"name":"to","type":"address"}],"name":"delegate","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[],"name":"winningProposal","outputs":[{"name":"winningProposal","type":"uint8"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"voter","type":"address"}],"name":"giveRightToVote","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"proposal","type":"uint8"}],"name":"vote","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"inputs":[{"name":"_numProposals","type":"uint8"}],"payable":false,"stateMutability":"nonpayable","type":"constructor"}];
const contractOwner = {
addr: '0xc78d53a4cc317dc3d3f7ee3d2ea639f77b2615d8',
key: '498EE239A44F1E3A48997CE39DB5DBAEEDBED972C43338D260887B06185FCFAF'
};

sendToken('0xc78d53a4cc317dc3d3f7ee3d2ea639f77b2615d8', '10');

function sendToken(receiver, amount) {
console.log(`Start to send ${amount} tokens to ${receiver}`);
const contract = web3.eth.contract(contractAbi).at(contractAddr);
const data = contract.transfer.getData(receiver, amount * 1e18);
const gasPrice = web3.eth.gasPrice;
const gasLimit = 90000;
const rawTransaction = {
  'from': contractOwner.addr,
  'nonce': web3.toHex(web3.eth.getTransactionCount(contractOwner.addr)),
  'gasPrice': web3.toHex(gasPrice),
  'gasLimit': web3.toHex(gasLimit),
  'to': contractAddr,
  'value': 0,
  'data': data,
  'chainId': 1
};

const privKey = new Buffer(contractOwner.key, 'hex');
const tx = new Tx(rawTransaction);
tx.sign(privKey);
const serializedTx = tx.serialize();
web3.eth.sendRawTransaction('0x' + serializedTx.toString('hex'), function (err, hash) {
  if (err) {
    console.log(err);
  }

  console.log(hash);
});
}
</script>
