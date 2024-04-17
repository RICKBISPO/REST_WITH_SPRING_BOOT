# REST_WITH_SPRING_BOOT

## Calculadora REST com Spring Boot
Esta é uma aplicação simples de calculadora REST desenvolvida com Spring Boot. A calculadora permite que você realize operações matemáticas básicas como soma, subtração, multiplicação, divisão e média.

## Como Usar
Para usar a calculadora, você pode fazer solicitações HTTP para o seguinte endpoint:

`http://localhost:8080/{operacao}/{numeroUm}/{numeroDois}`

Substitua {operacao} com a operação desejada (sum para soma, sub para subtração, multi para multiplicação, div para divisão e avg para média), {numeroUm} e {numeroDois} com os números que você deseja calcular.

## Exemplos de Uso
- Soma: Para calcular a soma de 2 e 3, faça uma solicitação GET para: http://localhost:8080/sum/2/3
- Subtração: Para calcular a subtração de 5 e 3, faça uma solicitação GET para: http://localhost:8080/sub/5/3
- Multiplicação: Para calcular a multiplicação de 4 e 6, faça uma solicitação GET para: http://localhost:8080/mult/4/6
- Divisão: Para calcular a divisão de 10 por 2, faça uma solicitação GET para: http://localhost:8080/div/10/2
- Média: Para calcular a média de 4 e 8, faça uma solicitação GET para: http://localhost:8080/avg/4/8
