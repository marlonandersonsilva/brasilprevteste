@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping
public ResponseEntity<Iterable<Client>> getClients() {
    var data = clientRepository.findAll();
    return new ResponseEntity<>(data, HttpStatus.OK);
}

   @PostMapping(produces="application/json")
public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody Client client) {
    Optional<Client> cpf = clientRepository.findClientByCpf(client.getCpf());
    Optional<Client> email = clientRepository.findClientByEmail(client.getEmail());
    ClientResponse response = new ClientResponse();
    if(cpf.isPresent() || email.isPresent()){
        if (cpf.isPresent() && email.isPresent()) {
            response.setMessage("E-mail is required and must be unique. " +
                    "CPF is required and must be unique");
        } else if(email.isEmpty()) {
            response.setMessage("CPF is required and must be unique");
        } else {
            response.setMessage("E-mail is required and must be unique.");
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    else{
        var newClient = clientRepository.save(client);
        response.setClient(newClient);
        response.setMessage("Success");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
 @PutMapping(path = "/{id}")
public ResponseEntity<ClientResponse> updateClient(@RequestBody Client client, @PathVariable int id) {
    boolean data = clientRepository.existsById(id);
    ClientResponse response = new ClientResponse();
    if(data){
        client.setId(id);
        clientRepository.save(client);
        response.setClient(client);
        response.setMessage("Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }else{
        response.setMessage("Client id: "+id+" cannot be found");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
    @GetMapping(path = "/{id}")
public ResponseEntity<ClientResponse> getClientById(@PathVariable int id) {
    var client = clientRepository.findById(id);
    ClientResponse response = new ClientResponse();
    if(client.isPresent()){
        response.setClient(client.get());
        response.setMessage("Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    response.setMessage("Client id: "+id+" cannot be found");
    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
}
  @DeleteMapping(path = "/{id}")
public ResponseEntity<ClientResponse> deleteClient(@PathVariable int id) {
    Optional<Client> data = clientRepository.findById(id);
    ClientResponse response = new ClientResponse();
    if(data.isPresent()){
        clientRepository.delete(data.get());
        response.setMessage("Client deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    response.setMessage("Client id: "+id+" cannot be found");
    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
} 