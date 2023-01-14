// window.onload=function(){
//     document.addEventListener('change', fileUpload)
// }

// function fileUpload(e) {
//     let fileInput = document.getElementsByClassName("ex_file");
//     let text =document.getElementById("mbox")
//     for (let i = 0; i < fileInput.length; i++) {
//         if (fileInput[i].files.length > 0) {
//             for (let j = 0; j < fileInput[i].files.length; j++) {
//                 text.innerHTML+=`<p>${fileInput[i].files[j].name}</p>`; // 파일명 출력
//             }
//         }
//     }
//     text=null;
// }

// ------------------------------------------------------------------------------

const { createApp } = Vue;

createApp({
  data() {
    return {
      search_msg: "",
    };
  },
  methods: {
    search_db() {
      console.log(this.search_msg);

      //이전 검색 데이터 내용을 지우기
      const person_box = document.getElementById("modal_search_result");
      const child_all = document.querySelectorAll("#modal_search_result > *");
      for (i = 0; i < child_all.length; i++) {
        person_box.removeChild(child_all[i]);
      }

      // unfilled : 리턴값에 의해 반복 돌려야함
      for (i = 0; i < 5; i++) {
        search_person(this.search_msg);
      }
    },
  },
}).mount(".sb-nav-fixed");

// ------------------------------------------------------------------------------
let fileLists = []; // 전체 파일 리스트 객체
const exte = ["jpg", "jpeg", "png", "gif"]; // 확장명

const inputFile = document.querySelector("#inputFile");
const resultFile = document.querySelector("#resultFile");
const file_count = document.getElementById("file_count");
const total_sizes = document.getElementById("total_size");

inputFile.addEventListener(
    "change",
    function (e) {
      return readURL(this.files);
    },
    false
);

const readURL = (input) => {
  if (fileLists.length == 0) {
    // 최초 파일 업로드
    for (let i = 0; i < input.length; i++) {
      fileLists.push(input[i]);
    }
  } else {
    // 리스트에 추가적으로 파일 업로드
    for (let i = 0; i < input.length; i++) {
      // 중복된 파일 검사(이름 기준)
      var isExist = false;
      for (let j = 0; j < fileLists.length; j++) {
        if (input[i].name == fileLists[j].name) {
          isExist = true;
        }
      }
      if (isExist == false) {
        fileLists.push(input[i]);
      }
    }
  }

  // 결과창 초기화
  resultFile.innerHTML = "";
  // 전체 파일 사이즈 초기화
  let total_size = 0;

  // 선택된 파일만큼 반복
  for (let i = 0; i < fileLists.length; i++) {
    total_size += Math.round(fileLists[i].size / 1024);

    //File정보를 읽을 수 있는 FileReader 호출
    const reader = new FileReader();

    //File 불러오기가 끝나면 실행될 함수
    reader.onload = function (e) {
      const img = new Image();

      // image파일의 blob 이 생성됨. blob -> 바이너리 오브젝트
      img.src = e.target.result;

      // 생성된 데이터를 템플릿 문자열에 넣어준다.
      return (resultFile.innerHTML += `
                <dl>
                    <dt class='total_size_kb'>${total_size}</dt>
                    <dd>${fileLists[i].name} ${
          Math.round(fileLists[i].size / 1024) + "kb"
      } <span onclick="deleteBtn(${i})" style="color: red;cursor: pointer;">[X]</span></dd>
                </dl>
                `);
    };
    // readAsDataURL은 Blob, File 을 읽을 수 있다.
    reader.readAsDataURL(fileLists[i]);
  }

  file_count.innerHTML = `파일 <span style="color: red;"> ${fileLists.length} </span> 개`;
  total_sizes.innerHTML = total_size + " ";

  // 파일 리스트가 비어있으면 input[:file]의 value 값 초기화 ==> input[:file] event 자체 이슈임.
  if (fileLists.length == 0) {
    inputFile.value = "";
  }
};

/* 박스 안에서 Drag를 Drop했을 때 */
resultFile.ondrop = (e) => {
  e.preventDefault();

  // 드롭된 파일 데이터를 파일 리스트에 넣음. (중복 제거, 다중파일 업로드 가능)
  var data = e.dataTransfer.files;
  console.log(data);
  for (let i = 0; i < data.length; i++) {
    var isExist = false;
    var isExistExt = false;
    for (let j = 0; j < fileLists.length; j++) {
      if (data[i].name == fileLists[j].name) {
        isExist = true;
      }
    }

    var fileName = data[i].name;
    var fileNameArr = fileName.split(".");
    var ext = fileNameArr[fileNameArr.length - 1];

    if ($.inArray(ext, exte) == -1) {
      alert(`등록 불가 확장자 ${ext}가 포함되었습니다.`);
      isExistExt = true;
    }
    if (!isExist && !isExistExt) {
      fileLists.push(data[i]);
    }
  }
  readURL(fileLists);
};

resultFile.ondragover = (e) => {
  e.preventDefault(); // 이 부분이 없으면 ondrop 이벤트가 발생하지 않습니다.
};

// 파일 삭제
function deleteBtn(index) {
  // 파일 리스트에서 인덱스에 부합한 배열 제거
  fileLists.splice(index, 1);

  // 리스트 다시 그려주기
  readURL(fileLists);
}

//------------------------------------------------------------------------
const pobtn = document.querySelector("#pobtn");

pobtn.addEventListener(
    "change",
    function (e) {
      return readURL2(this.files);
    },
    false
);
const readURL2 = (input) => {
  // html 에 그리려고 만든 화살표함수

  if (input.length == 0) {
    document.getElementById("poBox2").innerHTML = `파일 끌어다 추가하기`;
  } else {
    document.getElementById("poBox2").innerHTML = `<dd>${input[0].name} ${
        Math.round(input[0].size / 1024) + "kb"
    } <span onclick="deleteBtn2()" style="color: red;cursor: pointer;">[X]</span></dd>`;
  }

  console.log(input);
};

poBox2.ondrop = (e) => {
  e.preventDefault();

  var data = e.dataTransfer.files;
  console.log(data);

  if (pobtn.files.length != 0) {
    pobtn.value = ""; // input  태그에서 받은 값
  }
  pobtn.files = data; // 드래그엔 드롭으로 받아온 값을  input 태그에서 받은 값과 같게 하기 위해서 넘김
  readURL2(pobtn.files);
};

poBox2.ondragover = (e) => {
  e.preventDefault(); // 이 부분이 없으면 ondrop 이벤트가 발생하지 않습니다.
};

function deleteBtn2() {
  // 파일 리스트에서 인덱스에 부합한 배열 제거
  pobtn.value = "";

  // 리스트 다시 그려주기
  readURL2(pobtn.files);
}
//------------------------------------------------------------------------
const babtn = document.querySelector("#babtn");

babtn.addEventListener(
    "change",
    function (e) {
      return readURL3(this.files);
    },
    false
);
const readURL3 = (input) => {
  // html 에 그리려고 만든 화살표함수

  if (input.length == 0) {
    document.getElementById("baBox2").innerHTML = `파일 끌어다 추가하기`;
  } else {
    document.getElementById("baBox2").innerHTML = `<dd>${input[0].name} ${
        Math.round(input[0].size / 1024) + "kb"
    } <span onclick="deleteBtn3()" style="color: red;cursor: pointer;">[X]</span></dd>`;
  }

  console.log(input);
};

baBox2.ondrop = (e) => {
  e.preventDefault();

  var data = e.dataTransfer.files;
  console.log(data);

  if (babtn.files.length != 0) {
    babtn.value = ""; // input  태그에서 받은 값
  }
  babtn.files = data; // 드래그엔 드롭으로 받아온 값을  input 태그에서 받은 값과 같게 하기 위해서 넘김
  readURL3(babtn.files);
};

baBox2.ondragover = (e) => {
  e.preventDefault(); // 이 부분이 없으면 ondrop 이벤트가 발생하지 않습니다.
};

function deleteBtn3() {
  // 파일 리스트에서 인덱스에 부합한 배열 제거
  babtn.value = "";

  // 리스트 다시 그려주기
  readURL3(babtn.files);
}

// ----------------------------------------------------------------------
function add() {
  const vBox = document.getElementById("vBox");

  const plusAndminus = document.getElementById("only_flex_box");

  const newNode = document.createElement("p");

  newNode.innerHTML += `<div class="vBox1">
      <div>
        <input type="text" placeholder="제목(ex.메인예고편, 현장예고편)" class="vBox2">
      </div>
      <div>
        <input type="text" placeholder="URL주소"  class="vBox2">
      </div>
    </div>`; // 파일명 출력

  vBox.insertBefore(newNode, plusAndminus);
}

function minus() {
  const vBox = document.getElementById("vBox");
  console.log(vBox);

  const removeNode = document.querySelector("#vBox > p");
  console.log(removeNode);

  vBox.removeChild(removeNode);
}

// ----------------------------------------------------------------------
function search() {
  window.open("https://www.naver.com/", "", "_blank");
}
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

//전역변수 배열에 select해서 넘어온 ott명 저장
let ottSave = "";

function createOtt(ott) {
  console.log(ott);

  ottSave = ott;
}

function ottVisible() {
  if (ottSave == "티빙") {
    const tving_box = document.getElementById("tving_box");
    //tving_box =
    tving_box.classList.add("visible");
  }
  if (ottSave == "웨이브") {
    const wave_box = document.getElementById("wave_box");
    wave_box.classList.add("visible");
  }
  if (ottSave == "디즈니플러스") {
    const disney_box = document.getElementById("disney_box");
    disney_box.classList.add("visible");
  }
  if (ottSave == "왓챠") {
    const watcha_box = document.getElementById("watcha_box");
    watcha_box.classList.add("visible");
  }
  if (ottSave == "넷플릭스") {
    const netflix_box = document.getElementById("netflix_box");
    netflix_box.classList.add("visible");
  }
  if (ottSave == "쿠팡플레이") {
    const coupang_box = document.getElementById("coupang_box");
    coupang_box.classList.add("visible");
  }
}

// ----------------------------------------------------------------------
// 티빙 박스 none으로 만들기
const tving_box_X = document.getElementById("tving_box_X");
tving_box_X.addEventListener("click", pop_out);

const wave_box_X = document.getElementById("wave_box_X");
wave_box_X.addEventListener("click", pop_out);

const disney_box_X = document.getElementById("disney_box_X");
disney_box_X.addEventListener("click", pop_out);

const watcha_box_X = document.getElementById("watcha_box_X");
watcha_box_X.addEventListener("click", pop_out);

const netflix_box_X = document.getElementById("netflix_box_X");
netflix_box_X.addEventListener("click", pop_out);

const coupang_box_X = document.getElementById("coupang_box_X");
coupang_box_X.addEventListener("click", pop_out);

function pop_out(e) {
  e.target.parentNode.parentNode.classList.remove("visible");
}

// ----------------------------------------------------------------------
function person_search_visible() {
  const person_search_modal = document.getElementById("person_search_modal");
  person_search_modal.classList.add("visible");
}

function search_cancel() {
  const person_search_modal = document.getElementById("person_search_modal");
  person_search_modal.classList.remove("visible");
}

// ----------------------------------------------------------------------

// unfilled : 반복문에 의해 불려질 인물 한사람의 정보를 담은 div 생성
function search_person(person) {
  console.log("검색어가 바뀌어서 테이블 탐색!");
  const person_box = document.getElementById("modal_search_result");

  const newNode = document.createElement("div");
  newNode.innerHTML = `${person}님의 인물정보를 시각화한div`;

  person_box.appendChild(newNode);
}
