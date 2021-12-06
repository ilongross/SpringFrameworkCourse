package home_work.hw3.controller;

import home_work.hw3.model.BankBookDto;
import home_work.hw3.service.BankBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-book")
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<List<BankBookDto>> getUserBankBooks(@PathVariable Integer userId) throws Exception {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(bankBookService.getUserBankBooks(userId));
    }

    @GetMapping("/{bankBookId}")
    public ResponseEntity<BankBookDto> getByBankBookId(@PathVariable Integer bankBookId) throws Exception {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(bankBookService.getByBankBookId(bankBookId));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto bankBookDto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bankBookService.createBankBook(bankBookDto));
    }

    @PutMapping
    public ResponseEntity<BankBookDto> updateBankBook(@RequestBody BankBookDto bankBookDto) throws Exception {
        return ResponseEntity
                .accepted()
                .body(bankBookService.updateBankBook(bankBookDto));
    }

    @DeleteMapping("/{bankBookId}")
    public void deleteBankBookById(@PathVariable Integer bankBookId) throws Exception {
        bankBookService.deleteBankBookById(bankBookId);
    }

    @DeleteMapping("/by-user-id/{userId}")
    public void deleteAllUserBankBooks(@PathVariable Integer userId) throws Exception {
        bankBookService.deleteAllUserBankBooks(userId);
    }

}
