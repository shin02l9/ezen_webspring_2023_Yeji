
import axios from 'axios';

export default function BoardWrite( props ){

      // 1. 등록함수
      const boardWrite = (e) => {
            // 1. 폼 가져오기 [ 첨부파일 ]
            let boardForm = document.querySelectorAll('.boardForm')[0];
            let boardFormData = new FormData(boardForm);

            // 2. axios 전송
            axios.post("/board/do", boardFormData)
                .then( result => { console.log(result)
                    if(result){
                        alert('게시글 등록 성공');
                        window.location.href = '/board/list';
                    } else { alert(' 게시글 등록 실패')}


                } );

      }



    return(<>
        <div>
            <h3> 게시물 쓰기 </h3>
            <form className="boardForm" >
                <h4> 제목 </h4>
                <input type="text" name="btitle" />
                <h4> 내용 </h4>
                <textarea  name="bcontent" > </textarea>
                {/*<h4> 첨부파일 </h4>
                <input type ="file" name="bfile" />*/}
                <button type="button" onClick={ boardWrite } > 등록하기 </button>
            </form>
        </div>
    </>)
}