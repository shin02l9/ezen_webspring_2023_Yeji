import { Link } from 'react-router-dom';
export default function Footer( props ){
    return(<>
    <footer className="footerAll">
        <div className="footerBox">
            <nav className="footerBoxTop">
                <a href="#" target=''> 회사소개 </a>
                <a href="#" target=''> 개인정보규정 </a>
                <a href="#" target=''> 환불규정 </a>
                <a href="#" target=''> 찾아오시는길 </a>
                <a href="#" target=''> 고객센터 </a>
            </nav>
            <hr/>
            <p className="footerBoxBottom">
                <div> 이젠 리액트 </div>
                <div> yeji199602@naver.com </div>
                <div> 01084640000 </div>
                <div> Copyright 2023. ezenreact. All Rights Reserved. </div>
            </p>
        </div>
    </footer>

    </>)
}