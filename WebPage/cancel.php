<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
require_once('iamport.php');
$iamport = new Iamport('3197819018018779', 'wsLCINx3ZsOPALTJBZQtIAoTkGcmKfFhIqTqerudUj3u5WeGV49EFyy0fLn012hJtaaHCFUwYX13UfKQ');
$result = $iamport->cancel(array(
	'imp_uid'		=> '거래 건의 imp_uid', 		//merchant_uid에 우선한다
	'merchant_uid'	=> '거래 건의 merchant_uid', 	//imp_uid 또는 merchant_uid가 지정되어야 함
	'amount' 		=> 1000,					//amount가 생략되거나 0이면 전액취소. 금액지정이면 부분취소(PG사 정책별, 결제수단별로 부분취소가 불가능한 경우도 있음)
	'reason'		=> '취소테스트',				//취소사유
	'refund_holder' => '환불될 가상계좌 예금주', 		//이용 중인 PG사에서 가상계좌 환불 기능을 제공하는 경우. 일반적으로 특약 계약이 필요
	'refund_bank',	=> '환불될 가상계좌 은행코드',
	'refund_account'=> '환불될 가상계좌 번호'
));
?>
cccccccccccccccxs
