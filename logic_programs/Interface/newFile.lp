service(StockQuoteService).
binding(StockQuoteSoapBinding).
operation(GetLastTradePrice, tns:GetLastTradePriceInput, tns:GetLastTradePriceOutput).
type(TradePriceRequest, tickerSymbol).
type(tickerSymbol, string).
type(TradePrice, price).
type(price, float).
type(body, xsd1:TradePriceRequest).
type(body, xsd1:TradePrice).
messagePart(GetLastTradePriceInput, body).
messagePart(GetLastTradePriceOutput, body).
