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

const { createApp } = Vue;

let searchVue = createApp({
  data() {
    return {
      search_msg: "",
      itemlist: {},
      itemlist2: {},
    };
  },
  methods:{
    chosen(idx){
      let flag = true

      for( let i of searchVue.itemlist2){
        if(idx == i.perIdx){
          personsArr.push(i)
        }
      }

      return flag
    }
  }
}).mount(".sb-nav-fixed");

function search_db() {
  console.log(searchVue.search_msg);
  //이전 검색 데이터 내용을 지우기
  const person_box = document.getElementById("modal_search_result");
  const child_all = document.querySelectorAll("#modal_search_result > *");

  // ajax로 정보 받아오기
  $.ajax({
    url: "/api/movie/searchKey",
    type: "GET",
    dataType: "json",
    processData: true,
    contentType: "application/json; charset=UTF-8",
    data: {searchKey : searchVue.search_msg},
    success: function(result){
      console.log('ajax 정보교환 성공!')
      for(let i of result.data){
        if(searchVue.itemlist2.length > 0){
          for(let j of searchVue.itemlist2){
            if(i.perIdx == j.perIdx){
              i.chooseCheck = "chosen";
            }
          }
        }
      }
      console.log(result.data);
      searchVue.itemlist = result.data;
    },
    error: function(){
      console.log("에러발생")
    }
  })
}

let personsArr = [];

function choosePerson(e){

  //버튼에 chosen 클래스 부여하기
  if(e.innerText == "선택"){
    e.innerText = "✔️선택됨"
    //선택을 누른 인물의 큰 박스
    const chooseBigBox = e.parentNode.parentNode;

    //큰 박스의 자식객체배열을 얻어옴
    const childArr = chooseBigBox.childNodes;

    //인물의 idx값 추출
    const perIdx = childArr[0].innerText

    //사진 src값을 추출
    const perPhoto = childArr[1].firstChild.getAttribute('src');

    //인물의 이름을 추출
    const perName = childArr[2].firstChild.innerText
    console.log(perName)

    //인물의 지위?
    const perRole = childArr[2].childNodes[1].innerText

    const person = {
      perIdx: childArr[0].innerText,
      perPhoto: childArr[1].firstChild.getAttribute('src'),
      perName: childArr[2].firstChild.innerText,
      perRole: childArr[2].childNodes[1].innerText
    }
    console.log(person)

    personsArr.push(person)

    searchVue.itemlist2 = personsArr

  }else{
    e.innerText = "선택"
    const idx = e.parentNode.parentNode.childNodes[0].innerText;

    //대입 배열 초기화
    personsArr = []

    //선택 인물 idx가 아닌 것들로 배열 재구성
    for( let i of searchVue.itemlist2){
      if(idx != i.perIdx){
        personsArr.push(i)
      }
    }
    //배열을 itemlist2에 삽입
    searchVue.itemlist2 = personsArr

  }


}

function minusItemlist2(e){
  //클릭한 인물의 idx 추출
  const idx = e.parentNode.firstChild.innerText;

  //대입 배열 초기화
  personsArr = []

  for( let i of searchVue.itemlist2){
    if(idx != i.perIdx){
      personsArr.push(i)
    }
  }
  searchVue.itemlist2 = personsArr
}

// ------------------------------------------------------------------------------


// ----------------------------------------------------------------------

//전역변수 배열에 select해서 넘어온 ott명 저장
let ottSave = "";

function createOtt(ott) {
  console.log(ott);
  ottSave = ott;
}

function ottVisible() {
  if (ottSave == "알라딘") {
    const aladin_box = document.getElementById("aladin_box");
    aladin_box.classList.add("visible");
    const aladin_url = document.getElementById("aladin_url");
    let videourl = document.getElementById("vurl").value;
    aladin_url.value=videourl;
  }
  if (ottSave == "Yes24") {
    const yes24_box = document.getElementById("yes24_box");
    yes24_box.classList.add("visible");
    const yes24_url = document.getElementById("yes24_url");
    let videourl = document.getElementById("vurl").value;
    yes24_url.value=videourl;
  }
  if (ottSave == "교보문고") {
    const kyobo_box = document.getElementById("kyobo_box");
    kyobo_box.classList.add("visible");
    const kyobo_url = document.getElementById("kyobo_url");
    let videourl = document.getElementById("vurl").value;
    kyobo_url.value=videourl;
  }
}

// ----------------------------------------------------------------------
// 티빙 박스 none으로 만들기
const aladin_box_X = document.getElementById("aladin_box_X");
aladin_box_X.addEventListener("click", pop_out);

const yes24_box_X = document.getElementById("yes24_box_X");
yes24_box_X.addEventListener("click", pop_out);

const kyobo_box_X = document.getElementById("kyobo_box_X");
kyobo_box_X.addEventListener("click", pop_out);


function pop_out(e) {
  e.target.parentNode.parentNode.classList.remove("visible");
}
function delval(str){
  document.getElementById(str).value=null;
}
//------------------------------------------------------------------------
//포스터
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
    } <span onclick="deleteBtn2()" style="color: red;cursor: pointer;">[X]
<input type="hidden" id="base">
</span></dd>`;
  }

  console.log(input);
};

poBox2.ondrop = (e) => {
  e.preventDefault();

  var data = e.dataTransfer.files;
  console.log(data);
  readLink(e.dataTransfer);
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
//배경사진
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
    } <span onclick="deleteBtn3()" style="color: red;cursor: pointer;">[X]
<input type="hidden" id="base3">
</span></dd>`;
  }

  console.log(input);
};

baBox2.ondrop = (e) => {
  e.preventDefault();

  var data = e.dataTransfer.files;
  console.log(data);
  readLink1(e.dataTransfer)
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
function add(e) {
  const vBox = document.getElementById("vBox");
  vBox.innerHTML += `<div class="vBox1">
      <div>
        <input type="text" placeholder="제목(ex.메인예고편, 현장예고편)" class="vBox2">
      </div>
      <div>
        <input type="text" placeholder="URL주소"  class="vBox2">
      </div>
    </div>`; // 파일명 출력
}

// ----------------------------------------------------------------------
function search() {
  window.open("https://www.naver.com/", "", "_blank");
}

// ----------------------------------------------------------------------
function person_search_visible() {
  const search_input = document.getElementById("modal_search_bar")
  search_input.value = ""

  const person_search_modal = document.getElementById("person_search_modal");
  person_search_modal.classList.add("visible");
}

function search_cancel() {
  const person_search_modal = document.getElementById("person_search_modal");
  person_search_modal.classList.remove("visible");
  searchVue.itemlist = ""
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
//-------------------------------------------------
//포스터
function readLink(input) {
  if (input.files && input.files[0]) {
    let reader = new FileReader();
    reader.onload = function (e) {
      $('#falseinput').attr('src', e.target.result);
      $('#base').val(e.target.result);
    };
    reader.readAsDataURL(input.files[0]);
  }
}

//배경사진
function readLink1(input) {
  if (input.files && input.files[0]) {
    let reader = new FileReader();
    reader.onload = function (e) {
      $('#falseinput').attr('src', e.target.result);
      $('#base3').val(e.target.result);
    };
    reader.readAsDataURL(input.files[0]);
  }
}
//---------------------------------------------------------------------
function sendit() {
  let bookThumbnail;
  let bookBackImg;


  try {
    bookThumbnail = document.querySelector("#base").value;
    console.log('포스터' + bookThumbnail.value);
  } catch (exception) {
    bookThumbnail = null;
  }

  try {
    bookBackImg = document.querySelector("#base3").value;
    console.log('배경사진' + bookBackImg.value);
  } catch (exception) {
    bookBackImg = null;
  }




  let bookTitle = document.querySelector("#bookTitle");
  let bookTitleSub = document.querySelector("#bookTitleSub");
  let bookWriter = document.querySelector("#bookWriter");
  let bookAtDate = document.querySelector("#bookAtDate");
  let bookPage = document.querySelector("#bookPage");
  let bookAge = $("#bookAge option:selected");
  let bookContentIdx = document.querySelector("#bookContentIdx");
  let bookPubSummary = document.querySelector("#bookPubSummary");


  let mycategory = document.querySelectorAll("#bookCategory + span li");
  let mycategory1;
  mycategory.forEach((element) => {
    mycategory1 += '/' + element.title;
  });
  let bookCategory = mycategory1.substring(10, mycategory1.length - 1)



  let bookPeople;
  let bookTime = document.querySelector("#bookTime");
  let bookSummary = document.querySelector("#bookSummary");



  let bookBuy=null;
  let shoplist=["aladin_url","yes24_url","kyobo_url"];
  let tempcntnum=0;
  for(let shop of shoplist){
    let shop_value = document.getElementById(shop).value
    if(shop_value!=""&&shop_value!=null){
      tempcntnum+=1;
      bookBuy+=shop_value+",";
    }
  }
  if(tempcntnum>0){
    bookBuy=bookBuy.substring(4,bookBuy.length-1);
  }



  console.log('제목' + bookTitle.value);
  console.log('부제' + bookTitleSub.value);
  console.log('작가'+ bookWriter.value);
  console.log('카테고리' + bookCategory);
  console.log('출간일' + bookAtDate.value);
  console.log('페이지' + bookPage.value);
  console.log('나이' + bookAge.val());
  console.log('내용' + bookSummary.value);
  console.log('목차' + bookContentIdx.value);
  console.log('출판사제공책소개' + bookPubSummary.value);
  console.log('구매가능한곳' + bookBuy)

  if (bookTitle.value == '') {
    alert('표기할 책제목을 입력하세요');
    return false;
  }
  if (bookTitleSub.value == '') {
    alert('부제를 입력하세요');
    return false;
  }
  if (bookWriter.value==''){
    alert('작가를 입력하세요');
    return false;
  }
  if (bookCategory == ',') {
    alert('카테고리를 입력하세요');
    return false;
  }
  if (bookAtDate.value == '') {
    alert('출간일 입력하세요');
    return false;
  }
  if (bookPage.value == '') {
    alert('페이지를 입력하세요');
    return false;
  }
  if (bookAge.val() == '') {
    alert('연령 등급을 입력하세요');
    return false;
  }
  if (bookSummary.value == '') {
    alert('내용을 입력하세요');
    return false;
  }


  fetch('http://localhost:8888/api/book', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({
      "transaction_time": `${new Date()}`,
      "resultCode": "ok",
      "description": "정상",
      "data": {
        "bookTitle": `${bookTitle.value}`,
        "bookTitleSub":`${bookTitleSub.value}`,
        "bookWriter":`${bookWriter.value}`,
        "bookCategory":bookCategory,
        "bookAtDate":`${bookAtDate.value}`,
        "bookPage":`${bookPage.value}`,
        "bookAge": `${bookAge.val()}`,
        "bookSummary": `${bookSummary.value}`,
        "bookThumbnail": bookThumbnail,
        "bookBackImg": bookBackImg,
        "bookPeople": `mrpark`,
        "bookContentIdx": `${bookContentIdx.value}`,
        "bookPubSummary": `${bookPubSummary.value}`,
        "bookBuy": bookBuy
      }
    }),
  })

      .then((response) => response.json())
      .then((data) => {
        if (data.resultCode == 'OK') {
          alert('등록성공');
          location.href='/contents/book';
        } else {
          alert('등록에 실패하였습니다. 다시한번 확인해주세요')
        }
      })
      .catch((err) => {
        alert('에러발생');
        location.reload();
      });


}