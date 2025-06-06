-- 코드를 입력하세요
SELECT MONTH(START_DATE), CAR_ID, COUNT(HISTORY_ID) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE CAR_ID IN (
    SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE date_format(START_DATE,'%Y-%m') between '2022-08' and '2022-10'
    GROUP BY CAR_ID
    HAVING count(HISTORY_ID) >= 5
) AND date_format(START_DATE,'%Y-%m') between '2022-08' and '2022-10'
GROUP BY CAR_ID, MONTH(START_DATE)
ORDER BY MONTH(START_DATE) ASC, CAR_ID DESC