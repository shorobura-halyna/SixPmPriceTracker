package com.sixpmpricetracker.mapper;

import com.sixpmpricetracker.dto.ProductHistoryDto;
import com.sixpmpricetracker.model.ProductHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductHistoryMapper {
    ProductHistoryMapper INSTANCE = Mappers.getMapper(ProductHistoryMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "oldPrise", target = "oldPrise")
    @Mapping(source = "newPrise", target = "newPrise")
    @Mapping(source = "oldDate", target = "oldDate")
    @Mapping(source = "newDate", target = "newDate")
    ProductHistoryDto productHistoryToProductHistoryDto(ProductHistory productHistory);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "oldPrise", target = "oldPrise")
    @Mapping(source = "newPrise", target = "newPrise")
    @Mapping(source = "oldDate", target = "oldDate")
    @Mapping(source = "newDate", target = "newDate")
    ProductHistory productHistoryDtoToProductHistory(ProductHistoryDto productHistoryDto);
}

