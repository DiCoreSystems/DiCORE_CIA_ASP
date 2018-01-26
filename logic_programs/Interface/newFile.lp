service(stockQuoteService, tns_StockQuoteBinding).
binding(stockQuoteSoapBinding).
binding(tns_StockQuoteBinding).
operation(tns_GetLastTradePrice, tns_GetLastTradePriceInput, tns_GetLastTradePriceOutput).
type(xsd1_TradePriceRequest, xsd1_tickerSymbol).
type(xsd1_tickerSymbol, xsd1_string).
type(xsd1_TradePrice, xsd1_price).
type(xsd1_price, xsd1_float).
input(tns_GetLastTradePrice, tns_GetLastTradePriceInput, tns_GetLastTradePriceInput).
output(tns_GetLastTradePrice, tns_GetLastTradePriceOutput, tns_GetLastTradePriceOutput).
messagePart(tns_GetLastTradePriceInput, xsd1_TradePriceRequest).
messagePart(tns_GetLastTradePriceOutput, xsd1_TradePrice).
