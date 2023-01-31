function submitreply(qnaIdx) {
    let QBox = document.getElementById('QBox');

    console.log("답변"+QBox.value);

    if(QBox.value!=''){
        if(QBox==null||QBox==''){
            alert('답변을 입력해주세요');
            return false;
        }
    }



    fetch(`http://localhost:9090/api/qna/qnaupdate`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "qnaIdx":`${qnaIdx}`,
                "qnaDtext":`${QBox.value}`,
            }
        }),
    })
        .then((res) => {
            alert('답변성공')
            location.href=`/qna/${qnaIdx}/qnaview`;
            return;
        })
        .catch((err) => {
            alert('에러!!');
            location.reload();
            return;
        });

}