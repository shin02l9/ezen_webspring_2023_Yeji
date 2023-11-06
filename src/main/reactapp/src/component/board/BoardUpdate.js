
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';

export default function BoardView( props ){

    const[ searchParams, setSearchParams ] = useSearchParams();
    const bno = searchParams.get('bno');




    return(<>
        <h3>BoardUpdate 게시물번호 = {bno}</h3>

      </>)
}