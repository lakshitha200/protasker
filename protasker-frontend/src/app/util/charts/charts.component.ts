import { CommonModule } from '@angular/common';
import { Component, ElementRef, ViewChild, AfterViewInit, OnDestroy, ChangeDetectorRef, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { Chart, ChartConfiguration, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-charts',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit, OnDestroy {
  @ViewChild('chartCanvas', { static: true }) private chartCanvas!: ElementRef;
  private chart: any;

   // Input properties
  @Input() chartType: 'bar' | 'line' | 'pie' | 'doughnut' = 'bar';
  @Input() chartData: any;
  @Input() chartOptions: any;

  constructor(private cdr: ChangeDetectorRef) {}

  // ngOnChanges(changes: SimpleChanges): void {
  //   if ((changes['chartType'] || changes['chartData'] || changes['chartOptions']) && this.chartData) {
  //     this.initChart();
  //   }
  // }

  ngOnInit(): void {
    console.log(this.chartType);
        console.log(this.chartData);
            console.log(this.chartOptions);
      this.initChart();
  }

  ngOnDestroy(): void {
    this.destroyChart();
  }

  private initChart(): void {
    // Ensure canvas element exists
    if (!this.chartCanvas?.nativeElement) {
      console.error('Canvas element not found!');
      return;
    }

    const ctx = this.chartCanvas.nativeElement.getContext('2d');
    if (!ctx) {
      console.error('Could not get canvas context!');
      return;
    }

    // Destroy previous chart if exists
    this.destroyChart();

    const config: ChartConfiguration = {
      type: this.chartType,
      data: this.chartData,
      options: this.chartOptions || {
        responsive: true,
        maintainAspectRatio: false,
  //       scales: {
  //   x: {
  //     ticks: { color: 'white' }, // X-axis text color
  //     grid: { color: '#3f3f3f' }, // Light gray grid lines (X-axis)
  //   },
  //   y: {
  //     ticks: { color: 'white' }, // Y-axis text color
  //     grid: { color: '#3f3f3f' }, // Light gray grid lines (X-axis)
  //   }
  // }
  // plugins: {
  //   legend: {
  //     labels: { color: 'white' }, // Legend text color
  //   },
  //   title: {
  //     color: 'white', // Chart title color
  //   }
  // }
      }
    };

    this.chart = new Chart(ctx, config);
  }

  private destroyChart(): void {
    if (this.chart) {
      this.chart.destroy();
      this.chart = null;
    }
  }
}