
// 사진 호출하기
import logo from '../../../logo.svg';
// CSS 파일 호출하기
import style from './Comment.css';

export default function Comment(props){
    return(<>
        <div className="wrap"> {/* 하나의 게시물 구역 */}
            <div>
                <img src={logo} className="pimg"/>
            </div>
            <div className="commentBox">
                <div className="commentName"> { props.name } </div> {/* 작성자 이름 */}
                <div className="commentContent"> { props.content } </div> {/* 작성자 내용 */}
            </div>
        </div>
    </>);
}