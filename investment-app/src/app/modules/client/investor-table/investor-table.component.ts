import { Component, OnInit, Input } from '@angular/core';
import { Investor } from '../investor';

@Component({
  selector: 'app-investor-table',
  templateUrl: './investor-table.component.html',
  styleUrls: ['./investor-table.component.css']
})
export class InvestorTableComponent implements OnInit {
  @Input() investors: Investor[] = [];
  constructor() { }

  ngOnInit() {
  }

}
