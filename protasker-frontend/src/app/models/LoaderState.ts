export interface LoaderState {
    loading: boolean;
    mode: 'fullscreen' | 'inline';   
    type: 'spinner' | 'dots' | 'bar';  
}