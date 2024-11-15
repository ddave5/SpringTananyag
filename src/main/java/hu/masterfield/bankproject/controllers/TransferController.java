package hu.masterfield.bankproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.masterfield.bankproject.datatypes.Confirmation;
import hu.masterfield.bankproject.interfaces.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TransferController {

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @RequestMapping(value="/transfer", method=RequestMethod.POST)
    public String transfer( @RequestParam("fromAccountName") String fromAccountName, 
                            @RequestParam("toAccountName") String toAccountName, 
                            @RequestParam("amount") int amount,
                            Model model,
                            HttpServletRequest request,
                            HttpSession session) 
    {
        Confirmation receipt = transferService.transfer(fromAccountName, toAccountName, amount); 
        System.out.println(receipt.getMessage());
        model.addAttribute("receipt", receipt);
        
        return "transferView";
    }
}
