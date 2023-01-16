let idCheck = false;

//아이디의 value가 바뀔 때마다 idCheck를 false로 바꿈
function idCheckReset(){
    idCheck = false;
}


//중복확인 눌렀을 때(onclick), 실행되는 id 중복확인 기능
function idCorrect(){
    const idValue = document.getElementById("adminId")

    console.log(idValue.value)
    //tb_admin_user에서 해당 값과 같은 값이 있는지 비교



}