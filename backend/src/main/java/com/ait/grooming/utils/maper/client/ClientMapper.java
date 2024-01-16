package com.ait.grooming.utils.maper.client;

import com.ait.grooming.dto.client.ClientDto;
import com.ait.grooming.model.User;

import java.util.List;

import static com.ait.grooming.utils.maper.discount.DiscountMapper.toDiscountDto;
import static com.ait.grooming.utils.maper.pet.PetMapper.allToPetDto;

public class ClientMapper {
    public static ClientDto toClientDto(User client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(client.getName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUserName(client.getUsername());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setRole(client.getRole());
        clientDto.setRegistrationDate(client.getRegistrationDate());
        clientDto.setBlocked(client.isBlocked());
    //    clientDto.setDiscounts(toDiscountDto(client.getDiscount()));
        clientDto.setPets(allToPetDto(client.getPets()));
        return clientDto;
    }

    public static List<ClientDto> allTOClientDto(List<User> clients) {
        return clients.stream().map(ClientMapper::toClientDto).toList();
    }
}
