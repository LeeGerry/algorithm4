package oa;

public class InvestmentsIn2012 {
/**
题目：给出如下表格，找出符合下列条件的行，再对TIV_2016这一列求和：TIV_2015有和其他行相同，LAT和LON其他行没有出现过。

| PID | TIV_2015 | TIV_2016 | LAT | LON |

|-----|————------|----------|-----|-----|

| 1   | 10       | 5        | 10  | 10  |

| 2   | 20       | 20       | 20  | 20  |

| 3   | 10       | 30       | 20  | 20  |

| 4   | 10       | 40       | 40  | 40  |

题解：这题根据要求的两个条件构造filter即可

SELECT ROUND(sum(TIV_2016), 2) AS TIV_2016
FROM insurance i
WHERE TIV_2015 IN (SELECT TIV_2015 FROM insurance GROUP BY TIV_2015 HAVING COUNT(TIV_2015) > 1) 
AND (LAT,LON) IN (SELECT LAT,lon FROM insurance GROUP BY LAT,LON HAVING COUNT(LAT) = 1);
*/
}
