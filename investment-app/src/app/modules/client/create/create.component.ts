import { Component, OnInit } from '@angular/core';
import { ClientService } from '../client.service';
import { FormBuilder, FormGroup, FormArray, FormControl, Validators } from '@angular/forms';
import { Router, Params, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})

export class CreateComponent implements OnInit {
  id: number;
  editMode = false;
  clientForm: FormGroup;
  public title: string;



  constructor(
    public fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    public clientService: ClientService

  ) {

  }


  ngOnInit() {

    this.route.params
      .subscribe(

        (params: Params) => {
          this.id = +params['id'];
          this.editMode = params['id'] != null;
          this.initForm();  let arr= [1,2,3].filter(val=>val!=2);
          console.log(arr);
          let c = 3;
          console.log(this.a());
        }
      );
  }


  submitForm() {
    if (this.editMode) {
      this.clientService.update(this.clientForm.value).subscribe(res => {
        console.log('Client created!'+this.clientForm.value);
        this.onCancel();
        console.log("after cancel");
      });
    }else{
    this.clientService.create(this.clientForm.value).subscribe(res => {
      console.log('Client created!'+this.clientForm.value);
      this.onCancel();
      console.log("after cancel");

    });
  }
  }

  onAddInvestor() {
    (this.clientForm.get("investors") as FormArray).push(this.investors);
  }

  onDeleteInvestor(index: number) {
    (this.clientForm.get("investors") as FormArray).removeAt(index);
  }
  onAddFund(investor) {
    investor.get("funds").push(this.funds);
  }

  onDeleteFund(investor,index: number) {
    investor.get("funds").removeAt(index);
  }
  onCancel() {
    this.router.navigateByUrl('/client');
  }


  private initForm() {
    console.log("in initForm");
    this.clientForm = this.fb.group({
        id: "",
        clientName: "",
        description: "",

     investors: this.fb.array([])
    });
    let clientName = '';
    let clientImagePath = '';
    let clientDescription = '';
    let clientInvestor = new FormArray([]);
    let investorFunds = new FormArray([]);
    console.log("this.editMode==>"+this.editMode)
    this.title="Create Client";
    if (this.editMode) {
      this.title="Edit Client";
      const client = this.clientService.getById(this.id);
      client.subscribe((myclient) => {
        if (myclient['investors']) {
           for (let investor of myclient.investors) {
            for (let fund of investor.funds) {
              investorFunds.push(
                new FormGroup({
                  'id': new FormControl(fund.id),
                  'name': new FormControl(fund.name, Validators.required)
                })
              )

            }
             clientInvestor.push(
              new FormGroup({
                'id': new FormControl(investor.id),
                'name': new FormControl(investor.name, Validators.required),
                'email': new FormControl(investor.email),
                'funds':investorFunds
              })
            );
          }
        }
         this.clientForm = new FormGroup({
          'id': new FormControl(myclient.id),
          'clientName': new FormControl(myclient.clientName, Validators.required),
          'description': new FormControl(myclient.description, Validators.required),
          'investors': clientInvestor
        });

      });
      console.log("=======>"+clientName)
     /* this.clientForm = new FormGroup({
        'id': new FormControl(myclient.id),
        'clientName': new FormControl(clientName, Validators.required),
        'description': new FormControl(clientDescription, Validators.required),
        'investors': clientInvestor
      });*/
    }
  }

  get investors(): FormGroup {
    return this.fb.group({
      name: "",
      email: "",
      funds: this.fb.array([])
    });
  }

  get funds(): FormGroup {
    return this.fb.group({
      name: ""
    });
  }
}

