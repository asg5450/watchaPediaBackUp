const {createApp} = Vue

createApp({
    data(){
        return {
            pwNoText: false,
            pwOkText: false,
        }
    },
    methods: {
        pwEquals(){
            console.log("비밀번호 확인 메소드 발동!")
            const adminPw = document.getElementById('adminPw')
            const adminPwRe = document.getElementById('adminPwRe')
            if(adminPw.value != adminPwRe.value){
                console.log('비밀번호가 달라서 if문 진입!')
                this.pwNoText = true
                this.pwOkText = false
            }else{
                console.log('비밀번호가 같아서 else문 진입!')
                this.pwNoText = false
                this.pwOkText = true
            }
        }

    }
}).mount('#main_box')


let idCheck = false;

//아이디의 value가 바뀔 때마다 idCheck를 false로 바꿈
function idCheckReset(){
    idCheck = false;
}


//중복확인 눌렀을 때(onclick), 실행되는 id 중복확인 기능
function idCorrect(){
    const idValue = document.getElementById("adminId")
    // console.log(idValue.value) //값 확인 완료!

    //tb_admin_user에서 해당 값과 같은 값이 있는지 비교하는 api 개발

}