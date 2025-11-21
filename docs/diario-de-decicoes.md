# 📘 Diário de Decisões do Projeto

A seguir estão documentadas todas as decisões arquiteturais, técnicas e funcionais tomadas ao longo do desenvolvimento do sistema, organizadas de forma clara e padronizada.

---

## 1. Criterização do Motor de Recomendação para Retorno de Investimento Único

### **Decisão**
O motor de recomendação sempre será recalculado a cada nova solicitação de simulação. Mesmo que existam diversos produtos compatíveis, o sistema retornará **apenas um único investimento recomendado**.

### **Justificativas**
- O retorno esperado pela API é **um único produto validado**.
- A recomendação deve sempre refletir o contexto mais recente do cliente.
- Perfis de risco precisam influenciar diretamente a seleção final.

### **Comportamento Definido**
1. **Primeira simulação sem perfil cadastrado:**
   - Se houver apenas um produto compatível → ele será retornado.
   - Se houver vários produtos → retorna-se **o produto de menor risco**.

2. **Cliente com perfil cadastrado:**
   - A escolha será feita com base no nível de risco do cliente.
   - Se não houver combinação exata, retorna-se o produto com risco mais próximo e dentre eles com a maior rentabilidade.

---

## 2. Procedimento de Registro de Investimento com Base na Simulação

### **Decisão**
Toda simulação realizada será registrada automaticamente como um investimento, pois o sistema trabalha com a premissa de **simulação = registro de uma operação única**.

### **Justificativas**
- Não há regras adicionais de aplicação financeira fornecidas no desafio.
- O sistema precisa armazenar resultados reais, não apenas simulações isoladas.
---

## 3. Motivo para o Tipo de Investimento Ser Campo Aberto (e não Enum)

### **Decisão**
O campo **tipo de investimento** será *aberto* (string) e **não** um enum.

### **Justificativas**
- O mercado possui uma grande variedade de categorias (CDB, LCI, LCA, Fundo Multimercado, FII, ETF, etc.).
- Novos tipos de investimento surgem constantemente.
- Utilizar enum obrigaria atualizações de código e deploy a cada novo tipo.
- Campo aberto → **flexibilidade**, **escalabilidade** e **aderência ao mercado real**.

---


## 4. Fórmula para Gerar o Score

### **Decisão**
O score do cliente será calculado com base em indicadores como:
- volume total investido
- frequência de movimentações
- liquidez 

O score alimenta a definição do **perfil de risco**.

### **Observação**
A fórmula é sempre aplicada — **nunca retornará valor vazio**.

---

## 5. Decisão sobre a Rota de Simulação por Dia e Produto

### **Decisão**
A rota que retorna valores por dia e produto sempre deve priorizar:
- **o último dia disponível**  
- aceitar um **parâmetro de data**, caso o cliente solicite outro dia

### **Comportamento**
- Se a data informada for válida → retorna simulações daquele dia.  
- Se inválida ou não existir → retorna dados do **dia atual** (default).  

### **Justificativas**
- Mantém consistência nos relatórios.
- Evita retornos vazios.
- Permite flexibilidade de consulta histórica.

---

## 6. Mapeamento entre Risco do Investimento e Perfil do Cliente

### **Decisão**
O nível de risco do produto será mapeado de acordo com o perfil do cliente da seguinte forma:

| Perfil do Cliente | Riscos Permitidos |
|------------------|-------------------|
| Conservador      | Baixo             |
| Moderado         | Baixo, Moderado   |
| Agressivo        | Baixo, Moderado, Alto |

### **Justificativa**
- Garante coerência na recomendação.
- Evita exposição indevida do investidor a produtos incompatíveis.
- Segue diretrizes comuns do mercado financeiro.

---

## 7. O Sistema Sempre Deve Retornar um Investimento

### **Decisão**
A API **nunca retornará lista vazia** em uma simulação ou recomendação.

### **Justificativas**
- Garantia de previsibilidade para o front-end.
- Compatibilidade com o modelo esperado do desafio.
- Melhor experiência de usuário.

---

