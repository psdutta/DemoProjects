package com.task1.demo.jaxb;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.task1.demo.wsdlStub.GetBankResponseType;

public class SoapClient extends WebServiceGatewaySupport {
	public GetBankResponseType getBank(String url, Object request){
		JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(url, request);
		return (GetBankResponseType) res.getValue();
		}
		}