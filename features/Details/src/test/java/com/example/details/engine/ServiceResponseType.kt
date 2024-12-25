package com.example.details.engine

sealed class ServiceResponseType{

    data object EmptyResponse: ServiceResponseType()

    data object EmptyResultList: ServiceResponseType()

    data object InvalidData: ServiceResponseType()

    data object GoodData: ServiceResponseType()

    data object Http404: ServiceResponseType()
}