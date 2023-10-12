console.log("phone js 실행");

doget();
function doget(){
    console.log("doget() 실행");

        let pTable = document.querySelector(".pTable");
        let HTML =`<tr>
                       <th> 이름 </th>
                       <th> 전화번호 </th>
                       <th>  </th>
                   </tr>`;
        $.ajax({
            url : "/phone",
            method : "get",
            data : {} ,
            success : function f(r){
                console.log(" get 통신성공 : ")
                console.log(r)

                // 이름 속성을 기준으로 오름차순 정렬
                r.sort(function(a, b) {
                    var nameA = a.pname.toLowerCase(); // 이름을 대소문자 구분 없이 비교
                    var nameB = b.pname.toLowerCase();
                    if (nameA < nameB) {
                        return -1; // a가 b보다 앞에 오도록 정렬
                    }
                    if (nameA > nameB) {
                        return 1; // b가 a보다 앞에 오도록 정렬
                    }
                    return 0; // 이름이 같을 경우 순서 변경 없음
                });

                // 정렬된 배열 확인
                console.log('정렬된 리스트 : '+r);


                r.forEach( (phonelist) => {
                    HTML +=`
                    <tr>
                        <td> ${phonelist.pname}  </td>
                        <td> ${phonelist.pphone}  </td>
                        <td>
                            <button onclick="doput(${phonelist.pno})" class="btn" type="button" > 수정 </button>
                            <button onclick="dodelete(${phonelist.pno})" class="btn" type="button" > 삭제 </button>
                        </td>
                    </tr>
                    `;
                });
                pTable.innerHTML = HTML;

            }
        })
}

function dopost(){
     console.log("dopost() 실행");

        let Pname = document.querySelector(".Pname");
        let Pphone = document.querySelector(".Pphone");

        let phoneInput = {
            pname : Pname.value,
            pphone : Pphone.value
        }
        let Json = JSON.stringify(phoneInput);

        $.ajax({
            url : "/phone",
            method : "post",
            contentType: "application/json",
            data : Json ,
            success : function f(r){
                console.log(" post 통신성공 : "+r)
                if(r){
                 alert("등록되었습니다.");
                 Pname.value ='';
                 Pphone.value ='';
                 doget();
                }
            }
        })
}

function doput(pno){
    console.log("doput() 실행");

    console.log('pno : '+pno);
    let newname = prompt('수정할 이름');
    console.log('newname : '+newname);
    let newphone = prompt('수정할 전화번호');
    console.log('newphone : '+newphone);

    let phoneInput = {
        pno : pno,
        pname : newname,
        pphone : newphone
    }
    let Json = JSON.stringify(phoneInput);

    $.ajax({
        url : "/phone",
        method : "put",
        data : Json ,
        contentType: "application/json",
        success : function f(r){
            console.log("통신성공 : "+r)

                if(r) { alert("상태가 수정 되었습니다.");}
                else { alert("수정을 실패했습니다.");}
                doget();
        }
    })
}

function dodelete(pno){
    console.log("dodelete() 실행");

    $.ajax({
        url : "/phone",
        method : "delete",
        data : { pno : pno } ,
        success : function f(r){
            console.log("통신성공 : "+r)
            if(r){
                 alert("삭제되었습니다.");
                 doget();
            }
        } ,
        error : function f(e){
            console.log("통신실패 : "+e)
        }
    })
}


