# language: pt

Funcionalidade: Propondo lances ao leilão

Cenário: Propondo um único lance válido
 Dado um lance valido
 Quando propoe o lance
 Então o lance eh aceito
 
Cenário: Propondo vários lances válidos
 Dado uma sequencia de lances validos
 | x  | nomeUsuario |
 | 10 | Haean |
 | 15 | Fernando |
 | 20 | Redney |
 | 25 | Jade |
 | 30 | Pietro |
 | 35 | Natasha |
 Quando propoe varios lances ao leilao
 Então os lances sao aceitos
 
Esquema do Cenário: Propondo vários lances inválidos
Dado um lance invalido de <x> reais
Quando propoe o lance
Então o lance nao deve ser aceito

Cenários:
| x  |
| 0  |
| -1 |

Cenário: Propondo uma sequência de lances
 Dado dois lances
 | x  | nomeUsuario |
 | 10 | Haean |
 | 15 | Haean |
 Quando propoe varios lances ao leilao
 Então o segundo lance nao deve ser aceito
 
 
 