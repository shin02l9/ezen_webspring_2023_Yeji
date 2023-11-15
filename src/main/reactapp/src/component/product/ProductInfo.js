
// 차트 라이브러리
import { BarChart } from '@mui/x-charts/Barchart'; // npm install @mui/x-charts
import { PieChart } from '@mui/x-charts/PieChart';

// 훅
import React, { useState, useEffect } from 'react';

// axios
import axios from 'axios';

export default function ProductInfo( props ){

    const [ barChartDate , setBarChartDate ] = useState([]);
    const [ pieChartDate , setPieChartDate ] = useState([]);

    // 1. 막대 차트에 필요한 데이터 요청 -------------------------
    const getBarChart = (e) => {
        axios.get("/product/barChart")
            .then( r => {
                console.log( r.data )
                setBarChartDate( r.data )
            })
    }
    useEffect( () => {getBarChart()} , [])
    // 2. 원형 차트에 필요한 데이터 요청 -------------------------
    const getPieChart = (e) => {
        axios.get("/product/pieChart")
            .then( r => {
                console.log( r.data )
                setPieChartDate( r.data )
            })
    }
    useEffect( () => {getPieChart()} , [])




    return(<>
        <h3>제품상태</h3>
        <div style={{ display: 'flex', justifyContent: 'center'  }}>
            <div>
                <h3> 막대 차트 </h3>
                { barChartDate.length == 0 ? '' :
                <BarChart
                  xAxis={[
                    {
                      id: 'barCategories',
                      data: barChartDate.map( (p) => { return p.pname }),
                      scaleType: 'band',
                    },
                  ]}
                  series={[
                    {
                      data: barChartDate.map( (p) => { return p.pstock }),
                    },
                  ]}
                  width={500}
                  height={300}
                />}
            </div>
            <div>
                <h3> 원형 차트 </h3>
                <PieChart
                  series={[
                    {
                      data: pieChartDate.map( (p,i) => { return { id : i, value : p.count, label : p.pcname } })
                    },
                  ]}
                  width={400}
                  height={200}
                />
            </div>
        </div>
    </>)
}