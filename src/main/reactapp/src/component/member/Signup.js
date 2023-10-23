export default function Signup( props ){
    return(<>
    <h3>Signup</h3>
    <form>
        이메일[아이디] : <input type="text" /> <br/>
        비밀번호 : <input type="password" /> <br/>
        비밀번호확인 : <input type="password" /> <br/>
        이름  : <input type="text" /> <br/>
        전화번호 : <input type="text" /> <br/>
        <button type="button">Signup</button>
    </form>
    </>)
}