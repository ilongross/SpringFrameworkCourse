package home_work.hw4.controller;

import home_work.hw4.model.dto.BankBookDto;
import home_work.hw4.service.BankBookService;
import home_work.hw4.validation.Created;
import home_work.hw4.validation.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bank-book")
@Validated
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping({"/by-user-id/{userId}", "/by-user-id/"})
    public ResponseEntity<List<BankBookDto>> getUserBankBooks(@NotNull @PathVariable Integer userId) throws Exception {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(bankBookService.getUserBankBooks(userId));
    }

    @GetMapping({"/{bankBookId}", "/"})
    public ResponseEntity<BankBookDto> getByBankBookId(@NotNull @PathVariable Integer bankBookId) throws Exception {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(bankBookService.getByBankBookId(bankBookId));
    }

    @Validated(Created.class)
    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@Valid @RequestBody BankBookDto bankBookDto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bankBookService.createBankBook(bankBookDto));
    }

    @Validated(Update.class)
    @PutMapping
    public ResponseEntity<BankBookDto> updateBankBook(@Valid @RequestBody BankBookDto bankBookDto) throws Exception {
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
