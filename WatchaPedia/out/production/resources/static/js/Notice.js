window.onload = () => {
  const status_btn = document.getElementById("status_btn");
  const status_text = document.getElementById("status_text");

  if (status_text.textContent == "") {
    const btnText = document.createTextNode("등록하기");
    status_btn.appendChild(btnText);
    const newText = document.createTextNode("미등록");
    status_text.appendChild(newText);
  }
};

function change_status() {
  if (status_text.textContent == "미등록") {
    status_text.textContent = "";
    const newText = document.createTextNode("등록");
    status_text.appendChild(newText);
    status_btn.textContent = "";
    const newText2 = document.createTextNode("해제하기");
    status_btn.appendChild(newText2);
  } else {
    status_text.textContent = "";
    const newText = document.createTextNode("미등록");
    status_text.appendChild(newText);
    status_btn.textContent = "";
    const newText2 = document.createTextNode("등록하기");
    status_btn.appendChild(newText2);
  }
}
