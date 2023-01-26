const btn = document.getElementById("btn");
console.log(btn);
btn.addEventListener("click", sendit);

function sendit() {
  const userpw = document.getElementById("userpw");
  const reuserpw = document.getElementById("reuserpw");
  const re_userpw = document.getElementById("re_userpw");

  if (userpw.value == "") {
    document.getElementById("atn").innerHTML =
      "<p style= 'color: red'>기존 비밀번호를 입력하세요</p>";
    userpw.focus();
    return false;
  }

  if (reuserpw.value == "") {
    document.getElementById("atn").innerHTML =
      "<p style= 'color: red'>새 비밀번호를 입력하세요</p>";
    reuserpw.focus();
    return false;
  }

  if (re_userpw.value == "") {
    document.getElementById("atn").innerHTML =
      "<p style= 'color: red'>새 비빌번호 확인칸을 입력해주세요</p>";
    re_userpw.focus();
    return false;
  }

  if (reuserpw.value != re_userpw.value) {
    document.getElementById("atn").innerHTML =
      "<p style= 'color: red'>비밀번호와 비밀번호 확인의 값이 다릅니다</p>";
    re_userpw.focus;
    return false;
  }
}
