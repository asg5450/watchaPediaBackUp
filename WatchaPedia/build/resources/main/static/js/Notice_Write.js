function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('preview').src = "";
  }
}

const spoid = document.getElementById('spoid')
spoid.addEventListener('change', pastel)

function pastel(){
  const ntcBtnColor = document.getElementById("ntcBtnColor");
  ntcBtnColor.value = spoid.value;
  console.log(`ntcBtnColor에 담은 값 : ${ntcBtnColor.value}`);
}

function submitCheck(){
  console.log("submitCheck 실행!")
  const ntcTitle = document.getElementById('ntcTitle');
  const file = document.getElementById('file');
  const ntcText = document.getElementById('ntcText');
  const ntcBtnText = document.getElementById('ntcBtnText');
  console.log("객체선정 완료!")

  if(ntcTitle.value == ''){
    alert('제목을 입력하세요');
    ntcTitle.focus()
    return false;
  }

  if(ntcText.value == ''){
    alert('공지내용을 입력하세요');
    ntcText.focus()
    return false;
  }

  if(ntcBtnText.value == ''){
    alert('광고버튼 텍스트를 입력하세요');
    ntcBtnText.focus()
    return false;
  }

  console.log("빈 값 체크완료!")

  console.log(`file.value : ${file.value}`)

  console.log(`file : ${file}`)

  console.log('출력확인 끝!')

  // fetch('http://localhost:9090/api/notice', {
  //   method: 'POST',
  //   headers: { 'Content-Type': 'application/json' },
  //   body: JSON.stringify({
  //     "transaction_time":`${new Date()}`,
  //     "resultCode":"ok",
  //     "description":"정상",
  //     "data":{
  //       "ntcTitle":`${ntcTitle.value}`,
  //       "file":`${file.value}`,
  //       "ntcText":`${ntcText.value}`,
  //       "ntcBtnText":`${ntcBtnText.value}`,
  //       "ntcBtnColor":`${ntcBtnColor.value}`,
  //     }
  //   }),
  // }).then((res) => {
  //   res.json()
  //   alert('등록성공!')
  //   location.href = '/notice';
  //   return;
  // })

  document.getElementById('frm').submit();


}