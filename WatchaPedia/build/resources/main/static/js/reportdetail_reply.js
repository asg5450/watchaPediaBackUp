const big_container = document.querySelector(".card-body");
console.log(big_container);

function blind_result() {
  const p_array = document.querySelectorAll(".card-body > p");
  if (p_array.length >= 1) {
    const text_deco = document.querySelector(".card-body > p:last-child");
    const style = document.createAttribute("style");
    style.value = "text-decoration:line-through";
    console.log(style);
    text_deco.setAttributeNode(style);
  }

  const newNode = document.createElement("p");
  newNode.innerHTML =
    "<div><p>처리내용 : 블라인드처리</p><p>처리일시 : 2022-02-02</p><p>관리자 : 이재원</p></div><hr>";
  big_container.appendChild(newNode);

  const spoiler_btn = document.getElementById("spoiler_btn");
  const noproblem_btn = document.getElementById("noproblem_btn");
  const blinder_btn = document.getElementById("blinder_btn");
  console.log(blinder_btn);
  blinder_btn.classList.add("selected");
  spoiler_btn.classList.remove("selected");
  noproblem_btn.classList.remove("selected");
}

function spoiler_result() {
  const p_array = document.querySelectorAll(".card-body > p");
  if (p_array.length >= 1) {
    const text_deco = document.querySelector(".card-body > p:last-child");
    const style = document.createAttribute("style");
    style.value = "text-decoration:line-through";
    console.log(style);
    text_deco.setAttributeNode(style);
  }

  const newNode = document.createElement("p");
  newNode.innerHTML =
    "<div><p>처리내용 : 스포일러처리</p><p>처리일시 : 2022-02-02</p><p>관리자 : 이재원</p></div><hr>";
  big_container.appendChild(newNode);

  const spoiler_btn = document.getElementById("spoiler_btn");
  const noproblem_btn = document.getElementById("noproblem_btn");
  const blinder_btn = document.getElementById("blinder_btn");

  console.log(spoiler_btn);
  spoiler_btn.classList.add("selected");
  blinder_btn.classList.remove("selected");
  noproblem_btn.classList.remove("selected");
}

function noproblem_result() {
  const p_array = document.querySelectorAll(".card-body > p");
  if (p_array.length >= 1) {
    const text_deco = document.querySelector(".card-body > p:last-child");
    const style = document.createAttribute("style");
    style.value = "text-decoration:line-through";
    console.log(style);
    text_deco.setAttributeNode(style);
  }

  const newNode = document.createElement("p");
  newNode.innerHTML =
    "<div><p>처리내용 : 문제없음처리</p><p>처리일시 : 2022-02-02</p><p>관리자 : 이재원</p></div><hr>";
  big_container.appendChild(newNode);

  const spoiler_btn = document.getElementById("spoiler_btn");
  const noproblem_btn = document.getElementById("noproblem_btn");
  const blinder_btn = document.getElementById("blinder_btn");

  console.log(noproblem_btn);
  noproblem_btn.classList.add("selected");
  blinder_btn.classList.remove("selected");
  spoiler_btn.classList.remove("selected");
}
