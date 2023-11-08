
/*
    mui 라는 리액트 전용 라이브러리
        1. 설치 ( 리액트 폴더에 설치하기! )
                npm install @mui/material @emotion/react @emotion/styled
                npm install @mui/material @mui/styled-engine-sc styled-components
        2. 예제
            1. 호출을 컴포넌트 상단에 하기
                import Button from '@mui/material/Button';
            2. 호출된 버튼을 바로 사용하기
                <Button variant="contained">Hello world</Button>;


*/

import axios from 'axios';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import style from './Board.css';
/*   ------------ MUI Table 관련 컨포넌트 ------------   */
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

/*   ------------ MUI 페이징처리 관련 ------------   */
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';

/*   ------------ MUI Table 샘플 ------------   */
function createData(name, calories, fat, carbs, protein) {
  return { name, calories, fat, carbs, protein };
}



export default function BoardList( props ){

//      axios 사용전 쓴 샘플데이터
//    let rows = [
//        { bno : 1 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 },
//        { bno : 2 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 },
//        { bno : 3 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 },
//        { bno : 4 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 },
//        { bno : 5 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 }
//    ];
    // 0. 스프링에게 전달 받은 객체
    let [ pageDto, setPageDto ] = useState({
        boardDtos : [],
        totalPages : 0,
        totalCount : 0

    });

    // 0. 스프링에게 전달할 객체
    let [ pageInfo, setPageInfo ] = useState({
        page : 1,
        key : '',
        keyword : ''
        }
    );

    console.log('pageInfo')
    console.log(pageInfo)



    // 1. axios를 이용한 스프링의 컨트롤과 통신
    useEffect ( () => {
        axios.get("/board/do", { params : { page : pageInfo.page, key :, pageInfo.key, keyword : pageInfo.keyword }})
        .then( r => {
            //console.log(r.data);
            setPageDto(r.data); // 응답받은 모든 게시물을 상태 변수에 저장한다.
            // setState : 해당 컴포넌트가 업데이트(새로고침/재랜더링/return재실행)
            // 무한루프에 빠지는 문제점 발생 ㅋㅋㅋㅋㅋㅋㅋ
            // 그래서 useEffect 으로 감싸준다.
        })
    } , [ pageInfo ])// -> 페이지가 변경될때마다 실행하기


    // 페이지 버튼을 클릭 했을때
    const onPageSelect = (e, value) => {
        //console.log('onPageSelect')
        //console.log(value)
        pageInfo.page = value;
        setPageInfo( {...pageInfo} )
    }

    // 검색 버튼을 클릭 했을때
    const onSearch = (e) => {


    }





  return (<>
          <h3> 게시물 목록 </h3>
          <a href="/board/write"> 글쓰기 </a>
          <p> 현재 페이지 번호 : { pageInfo.page }</p>


            <TableContainer component={Paper}>
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
              {/* 테이블 제목 구역 */}
                <TableHead>
                  <TableRow>
                    <TableCell align="right">번호</TableCell>
                    <TableCell align="right">제목</TableCell>
                    <TableCell align="right">작성자</TableCell>
                    <TableCell align="right">작성일</TableCell>
                    <TableCell align="right">조회수</TableCell>
                  </TableRow>
                </TableHead>
                {/* 테이블 내용 구역 */}
                <TableBody>
                  {pageDto.boardDtos.map((row) => (
                    <TableRow key={row.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                      <TableCell align="right">{row.bno}</TableCell>
                      <TableCell align="right">
                        <Link to={ "/board/view?bno="+row.bno }> {row.btitle} </Link>
                      </TableCell>
                      <TableCell align="right">{row.mno}</TableCell>
                      <TableCell align="right">{row.cdate}</TableCell>
                      <TableCell align="right">{row.bview}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>


        {/* 페이징 처리하기 */}
        <div className="PaginationBox" >
            <Pagination count={pageDto.totalPages}
                        onChange={onPageSelect} />
        </div>

        {/* 검색하기 */}
        <div className="searchbox">
            <select
                value={ pageInfo.key }
                onChange={ (e) => {
                    setPageInfo({...pageInfo , key : e.target.value })
                } }
            >
                <option value="btitle"> 제목 </option>
                <option value="bcontent"> 내용 </option>
            </select>
            <input type="text"
                value={ pageInfo.keyword }
                onChange={ (e) => {
                    setPageInfo({...pageInfo , keyword : e.target.value })
                } }
            />
            <button onClick={ onSearch } type="button"> 검색 </button>
        </div>
  </>);
}



