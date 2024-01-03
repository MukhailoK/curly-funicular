package com.ait.grooming.utils.maper.client;

import com.ait.grooming.dto.client.ClientDto;
import com.ait.grooming.model.Client;

import java.util.List;

import static com.ait.grooming.utils.maper.discount.DiscountMapper.allToDiscountDto;
import static com.ait.grooming.utils.maper.pet.PetMapper.allToPetDto;

public class ClientMapper {
    public static ClientDto toClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(client.getName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUserName(client.getUserName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setRegistrationDate(client.getRegistrationDate());
        clientDto.setBlocked(client.isBlocked());
        clientDto.setDiscounts(allToDiscountDto(client.getDiscounts()));
        clientDto.setPets(allToPetDto(client.getPets()));
        return clientDto;
    }

    public static List<ClientDto> allTOClientDto(List<Client> clients) {
        return clients.stream().map(ClientMapper::toClientDto).toList();
    }
}
