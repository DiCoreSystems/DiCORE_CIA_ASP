service(stockQuoteService).
binding(stockQuoteSoapBinding).
operation(getLastTradePrice, http*58**47**47*example*46*com*47*stockquote*46*wsdl*GetLastTradePriceInput, http*58**47**47*example*46*com*47*stockquote*46*wsdl*GetLastTradePriceOutput).
type(http*58**47**47*example*46*com*47*stockquote*46*xsd*TradePriceRequest, http*58**47**47*example*46*com*47*stockquote*46*xsd*tickerSymbol).
type(http*58**47**47*example*46*com*47*stockquote*46*xsd*tickerSymbol, http*58**47**47*example*46*com*47*stockquote*46*xsd*string).
type(http*58**47**47*example*46*com*47*stockquote*46*xsd*TradePrice, http*58**47**47*example*46*com*47*stockquote*46*xsd*price).
type(http*58**47**47*example*46*com*47*stockquote*46*xsd*price, http*58**47**47*example*46*com*47*stockquote*46*xsd*float).
input(getLastTradePrice, http*58**47**47*example*46*com*47*stockquote*46*wsdl*GetLastTradePriceInput, http*58**47**47*example*46*com*47*stockquote*46*wsdl*GetLastTradePriceInput).
output(getLastTradePrice, http*58**47**47*example*46*com*47*stockquote*46*wsdl*GetLastTradePriceOutput, http*58**47**47*example*46*com*47*stockquote*46*wsdl*GetLastTradePriceOutput).
messagePart(getLastTradePriceInput, http*58**47**47*example*46*com*47*stockquote*46*xsd*TradePriceRequest).
messagePart(getLastTradePriceOutput, http*58**47**47*example*46*com*47*stockquote*46*xsd*TradePrice).
