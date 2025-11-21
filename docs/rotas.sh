#!/bin/bash

# Script completo para execução direta de todas as requisições
BASE_URL="http://localhost:8082"
TOKEN_URL="http://localhost:8080/realms/quarkus/protocol/openid-connect/token"

echo "================================================"
echo "  EXECUÇÃO COMPLETA - SISTEMA DE INVESTIMENTOS"
echo "================================================"
echo

# Obter token
echo "1. OBTENDO TOKEN DE ACESSO..."
echo "----------------------------------------"
TOKEN_RESPONSE=$(curl -s -X POST "$TOKEN_URL" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -u "backend-service:secret" \
    -d "username=system-manager" \
    -d "password=1234" \
    -d "grant_type=password")

if [ $? -ne 0 ]; then
    echo "❌ Erro na requisição do token"
    exit 1
fi

ACCESS_TOKEN=$(echo "$TOKEN_RESPONSE" | grep -o '"access_token":"[^"]*' | cut -d'"' -f4)

if [ -z "$ACCESS_TOKEN" ]; then
    echo "❌ Erro ao extrair token da resposta: $TOKEN_RESPONSE"
    exit 1
fi

echo "✅ Token obtido com sucesso!"
echo

# 1. Listar perfil de risco
echo "2. LISTANDO PERFIL DE RISCO (cliente 10)"
echo "----------------------------------------"
curl -s -w "HTTP Status: %{http_code}\n" -X GET "$BASE_URL/perfil-risco/10" \
    -H "User-Agent: insomnia/9.3.3" \
    -H "Authorization: Bearer $ACCESS_TOKEN"
echo
echo

# 2. Investimentos por cliente
echo "3. INVESTIMENTOS POR CLIENTE (cliente 10)"
echo "----------------------------------------"
curl -s -w "HTTP Status: %{http_code}\n" -X GET "$BASE_URL/investimentos/10" \
    -H "User-Agent: insomnia/9.3.3" \
    -H "Authorization: Bearer $ACCESS_TOKEN"
echo
echo

# 3. Investimentos por perfil
echo "4. INVESTIMENTOS POR PERFIL (MODERADO)"
echo "----------------------------------------"
curl -s -w "HTTP Status: %{http_code}\n" -X GET "$BASE_URL/produtos-recomendados/MODERADO" \
    -H "User-Agent: insomnia/9.3.3" \
    -H "Authorization: Bearer $ACCESS_TOKEN"
echo
echo

# 4. Realizar simulação
echo "5. REALIZANDO SIMULAÇÃO DE INVESTIMENTO"
echo "----------------------------------------"
curl -s -w "HTTP Status: %{http_code}\n" -X POST "$BASE_URL/simular-investimento" \
    -H "Content-Type: application/json" \
    -H "User-Agent: insomnia/9.3.3" \
    -H "Authorization: Bearer $ACCESS_TOKEN" \
    -d '{
        "clienteId": 10,
        "valor": 100000,
        "tipoProduto": "FUNDO",
        "prazo": 90
    }'
echo
echo

# 5. Listar simulações
echo "6. LISTANDO SIMULAÇÕES"
echo "----------------------------------------"
curl -s -w "HTTP Status: %{http_code}\n" -X GET "$BASE_URL/simulacoes" \
    -H "User-Agent: insomnia/9.3.3" \
    -H "Authorization: Bearer $ACCESS_TOKEN"
echo
echo

# 6. Listar simulação por dia e produto
echo "7. SIMULAÇÃO POR DIA E PRODUTO (data: 2023-06-06)"
echo "----------------------------------------"
curl -s -w "HTTP Status: %{http_code}\n" -X GET "$BASE_URL/simulacoes/por-produto-dia?data=2023-06-06" \
    -H "User-Agent: insomnia/9.3.3" \
    -H "Authorization: Bearer $ACCESS_TOKEN"
echo
echo

# 7. Obter telemetria
echo "8. OBTENDO DADOS DE TELEMETRIA"
echo "----------------------------------------"
curl -s -w "HTTP Status: %{http_code}\n" -X GET "$BASE_URL/telemetria" \
    -H "User-Agent: insomnia/9.3.3" \
    -H "Authorization: Bearer $ACCESS_TOKEN"
echo
echo

echo "================================================"
echo "  EXECUÇÃO CONCLUÍDA!"
echo "================================================"